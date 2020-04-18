package com.infumia.t3sl4.tornadoplus.tornadoputil.misc;

import org.bukkit.entity.Player;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Bukkit;

public class ReflectionUtil
{
   private static ReflectionUtil instance;

   private String getNMSVersion() {
      return this.nms().split("\\.")[3];
   }

   private String nms() {
      final Object nmsServer = this.exec(Bukkit.getServer(), "getServer", new Object[0]);
      return (nmsServer != null) ? nmsServer.getClass().getPackage().getName() : "net.minecraft.server";
   }

   public String getCraftBukkitVersion() {
      return this.cb().split("\\.")[3];
   }

   public String cb() {
      return Bukkit.getServer().getClass().getPackage().getName();
   }

   public String getPackageName(final Object nmsObject) {
      return (nmsObject != null) ? nmsObject.getClass().getPackage().getName() : "";
   }

   public Class<?> getBukkitClass(final Object craftObject) {
      Class clazz;
      for (clazz = ((craftObject != null) ? craftObject.getClass() : null); clazz != null && clazz.getCanonicalName().contains(".craftbukkit."); clazz = clazz.getSuperclass()) {}
      return (Class<?>)clazz;
   }

   public Class<?> getCustomBukkitClass(final String className) {
      try {
         return Class.forName("org.bukkit.craftbukkit." + this.getNMSVersion() + "." + className);
      }
      catch (ClassNotFoundException var3) {
         return null;
      }
   }

   public Class<?> getNMSClass(final String name) {
      try {
         return Class.forName("net.minecraft.server." + this.getNMSVersion() + "." + name);
      }
      catch (ClassNotFoundException var3) {
         return null;
      }
   }

   public <T> T execStatic(final Class<?> clazz, final String methodName, final Object... args) {
      try {
         final Class[] argTypes = new Class[args.length];
         int ix = 0;
         final Object[] var6 = args;
         for (int var7 = args.length, var8 = 0; var8 < var7; ++var8) {
            final Object arg = var6[var8];
            argTypes[ix++] = this.getBukkitClass(arg);
         }
         final Method method = this.getMethod(clazz, methodName, argTypes);
         final boolean wasAccessible = method.isAccessible();
         method.setAccessible(true);
         Object var9;
         try {
            var9 = method.invoke(null, args);
         }
         finally {
            method.setAccessible(wasAccessible);
         }
         method.setAccessible(wasAccessible);
         return (T)var9;
      }
      catch (InvocationTargetException|IllegalAccessException|NoSuchMethodException var14) {
         var14.getCause().printStackTrace();
         return null;
      }
   }

   public <T> T execStatic(final Class<?> clazz, final String methodName, final Class<?>[] argTypes, final Object... args) {
      try {
         final Method method = this.getMethod(clazz, methodName, (Class[])argTypes);
         final boolean wasAccessible = method.isAccessible();
         method.setAccessible(true);
         Object var7;
         try {
            var7 = method.invoke(null, args);
         }
         finally {
            method.setAccessible(wasAccessible);
         }
         method.setAccessible(wasAccessible);
         return (T)var7;
      }
      catch (InvocationTargetException|IllegalAccessException|NoSuchMethodException var12) {
         var12.printStackTrace();
         return null;
      }
   }

   public <T> T exec(Object obj, String methodName, Class[] argTypes, Object... args) {
      if (obj == null)
         return null;
      Class<?> aClass = obj.getClass();
      try {
         Object var8;
         Method method = getMethod(aClass, methodName, argTypes);
         boolean wasAccessible = method.isAccessible();
         method.setAccessible(true);
         try {
            var8 = method.invoke(obj, args);
         } finally {
            method.setAccessible(wasAccessible);
         }
         return (T)var8;
      } catch (AbstractMethodError|InvocationTargetException|IllegalAccessException|NoSuchMethodException var13) {
         var13.printStackTrace();
         return null;
      }
   }

   public Method getMethod(final Class<?> aClass, final String methodName, final Class... argTypes) throws NoSuchMethodException {
      try {
         return aClass.getDeclaredMethod(methodName, (Class<?>[])argTypes);
      }
      catch (NoSuchMethodException var5) {
         return aClass.getMethod(methodName, (Class<?>[])argTypes);
      }
   }

   public Method findMethod(final Class<?> aClass, final Class returnType, final Class... argTypes) throws NoSuchMethodException {
      final List<Method> methods = this.findMethods(aClass, returnType, argTypes);
      if (methods.isEmpty()) {
         throw new NoSuchMethodException("No method matching " + returnType + " ?(" + Arrays.toString(argTypes) + ")");
      }
      if (methods.size() > 1) {
         throw new NoSuchMethodException("More than 1 method matching " + returnType + " ?(" + Arrays.toString(argTypes) + ") : " + methods);
      }
      return methods.get(0);
   }

   public List<Method> findMethods(final Class<?> aClass, final Class returnType, final Class... argTypes) throws NoSuchMethodException {
      final List<Method> methods = new ArrayList<Method>();
      for (final Method m : aClass.getDeclaredMethods()) {
         if (m.getReturnType() == returnType && m.getParameterTypes().length == argTypes.length) {
            try {
               final Method mLookup = aClass.getMethod(m.getName(), (Class<?>[])argTypes);
               if (mLookup != null) {
                  methods.add(mLookup);
               }
            }
            catch (NoSuchMethodException ex) {}
         }
      }
      return methods;
   }

   public <T> T exec(final Object obj, final String methodName, final Object... args) {
      if (obj == null) {
         return null;
      }
      final Class[] argTypes = new Class[args.length];
      int ix = 0;
      final Object[] var6 = args;
      for (int var7 = args.length, var8 = 0; var8 < var7; ++var8) {
         final Object arg = var6[var8];
         argTypes[ix++] = ((arg != null) ? arg.getClass() : null);
      }
      return this.exec(obj, methodName, argTypes, args);
   }

   public <T> T getField(final Class<?> clazz, final String fieldName) {
      try {
         final Field field = this.getFieldFromClass(clazz, fieldName);
         final boolean wasAccessible = field.isAccessible();
         field.setAccessible(true);
         Object var5;
         try {
            var5 = field.get(null);
         }
         finally {
            field.setAccessible(wasAccessible);
         }
         field.setAccessible(wasAccessible);
         return (T)var5;
      }
      catch (IllegalAccessException var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public <T> T getField(final Object obj, final String fieldName) {
      try {
         final Field field = this.getFieldInternal(obj, fieldName);
         final boolean wasAccessible = field.isAccessible();
         field.setAccessible(true);
         Object var5;
         try {
            var5 = field.get(obj);
         }
         finally {
            field.setAccessible(wasAccessible);
         }
         field.setAccessible(wasAccessible);
         return (T)var5;
      }
      catch (IllegalAccessException var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public Field getFieldInternal(final Object obj, final String fieldName) {
      return this.getFieldFromClass(obj.getClass(), fieldName);
   }

   public Field getFieldFromClass(final Class<?> aClass, final String fieldName) {
      if (aClass == null) {
         try {
            throw new NoSuchFieldException("Unable to locate field " + fieldName);
         }
         catch (NoSuchFieldException var7) {
            var7.printStackTrace();
         }
      }
      try {
         return aClass.getDeclaredField(fieldName);
      }
      catch (NoSuchFieldException var9) {
         try {
            return aClass.getField(fieldName);
         }
         catch (NoSuchFieldException var8) {
            var8.printStackTrace();
            return this.getFieldFromClass(aClass.getSuperclass(), fieldName);
         }
      }
   }

   public <T> void setField(final Object obj, final String fieldName, final T field) {
      try {
         final Field declaredField = this.getFieldInternal(obj, fieldName);
         final boolean wasAccessible = declaredField.isAccessible();
         declaredField.setAccessible(true);
         try {
            declaredField.set(obj, field);
         }
         finally {
            declaredField.setAccessible(wasAccessible);
         }
         declaredField.setAccessible(wasAccessible);
      }
      catch (IllegalAccessException var10) {
         var10.printStackTrace();
      }
   }

   public <T> T newInstance(final Class<?> clazz, final Object... args) {
      return this.newInstance(clazz.getName(), args);
   }

   public <T> T newInstance(final Class<?> clazz, final Class<?>[] argTypes, final Object... args) {
      return this.newInstance(clazz.getName(), argTypes, args);
   }

   public <T> T newInstance(String className, Class[] argTypes, Object... args) {
      try {
         Class<?> aClass = Class.forName(className);
         Constructor<?> constructor = aClass.getDeclaredConstructor(argTypes);
         return (T)constructor.newInstance(args);
      } catch (ClassNotFoundException|IllegalAccessException|NoSuchMethodException|InvocationTargetException|InstantiationException var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public <T> T newInstance(final String className, final Object... args) {
      final Class[] argTypes = new Class[args.length];
      int ix = 0;
      final Object[] var5 = args;
      for (int var6 = args.length, var7 = 0; var7 < var6; ++var7) {
         final Object arg = var5[var7];
         argTypes[ix++] = ((arg != null) ? arg.getClass() : null);
      }
      return this.newInstance(className, argTypes, args);
   }

   public List<String> dumpMethods(Class aClass) {
      List<Method> methods = Arrays.asList(aClass.getDeclaredMethods());
      List<String> methodDescriptions = new ArrayList<>();
      String version = getNMSVersion();
      Iterator<Method> var5 = methods.iterator();
      while (var5.hasNext()) {
         Method m = var5.next();
         List<String> parms = (List<String>)Arrays.<Class<?>>asList(m.getParameterTypes()).stream().map(f -> f.getName())

                 .collect(Collectors.toList());
         String parmString = Arrays.toString(parms.toArray((Object[])new String[0]));
         parmString = parmString.substring(1, parmString.length() - 1);
         String description = String.valueOf(Modifier.isPublic(m.getModifiers()) ? "public " : (Modifier.isPrivate(m.getModifiers()) ? "private " : "")) + (Modifier.isStatic(m.getModifiers()) ? "static " : "") + m.getReturnType() + " " + m.getName() + "(" + parmString + ")";
         description = description.replaceAll("class net.minecraft.server." + version + ".", "").replaceAll("net.minecraft.server." + version + ".", "").replaceAll("java.lang.", "");
         methodDescriptions.add(description);
      }
      List<String> list = new ArrayList<>();
      list.add(String.valueOf(aClass.toString().replaceAll("class net.minecraft.server." + version + ".", "").replaceAll("net.minecraft.server." + version + ".", "").replaceAll("java.lang.", "")) + ":");
      list.addAll((Collection<? extends String>)methodDescriptions.stream().sorted(String::compareTo).collect(Collectors.toList()));
      return list;
   }

   public void sendPacket(final Player player, final Object packet) {
      try {
         final Class<?> packetNms = this.getNMSClass("Packet");
         final Object nms = this.exec(player, "getHandle", new Object[0]);
         final Object playerConnection = this.getField(nms, "playerConnection");
         this.exec(playerConnection, "sendPacket", new Class[] { packetNms }, packet);
      }
      catch (Exception var6) {
         var6.printStackTrace();
      }
   }

   public static ReflectionUtil getInstance() {
      if (ReflectionUtil.instance == null) {
         ReflectionUtil.instance = new ReflectionUtil();
      }
      return ReflectionUtil.instance;
   }
}