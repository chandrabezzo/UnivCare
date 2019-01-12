# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# FAN
-dontwarn okio.**

# Room
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource

# Placeholderview
-keepattributes *Annotation*
-keepclassmembers class ** {
@com.mindorks.placeholderview.annotations.** <methods>;
}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for Gson  ----------

# Firebase SDK 2.5.2
-keep class com.firebase.** { *; }
-keep class org.apache.** { *; }
-keepnames class com.shaded.fasterxml.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }
-dontwarn org.apache.**
-dontwarn org.w3c.dom.**

# Only necessary if you downloaded the SDK jar directly instead of from maven.
-keep class com.shaded.fasterxml.jackson.** { *; }

# Firebase Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keepattributes SourceFile, LineNumberTable, *Annotation*

# If you are using custom exceptions, add this line so that custom exception types are skipped during obfuscation:
-keep public class * extends java.lang.Exception

# For Fabric to properly de-obfuscate your crash reports, you need to remove this line from your ProGuard config:
# -printmapping mapping.txt

# Fabric
-keep class io.fabric.sdk.android.services.events.EventsStorageListener
-keep class io.fabric.sdk.android.services.events.EventsStorage
-keep class io.fabric.sdk.android.services.common.CurrentTimeProvider
-keep class io.fabric.sdk.android.services.events.EventTransform
-keep class io.fabric.sdk.android.services.concurrency.Task
-keep class io.fabric.sdk.android.services.network.HttpMethod
-keep class io.fabric.sdk.android.services.network.HttpRequestFactory
-keep class io.fabric.sdk.android.Kit
-keep class io.fabric.sdk.android.services.common.IdManager
-keep class io.fabric.sdk.android.InitializationCallback
-keep class io.fabric.sdk.android.Fabric
-keep class io.fabric.sdk.android.services.common.IdManager$DeviceIdentifierType
-keep class io.fabric.sdk.android.services.persistence.PreferenceStore
-keep class io.fabric.sdk.android.services.persistence.FileStore
-keep class io.fabric.sdk.android.services.settings.PromptSettingsData
-keep class io.fabric.sdk.android.services.network.HttpRequest
-keep class io.fabric.sdk.android.services.settings.SessionSettingsData
-keep class io.fabric.sdk.android.services.settings.SettingsData
-keep class io.fabric.sdk.android.services.settings.BetaSettingsData
-keep class io.fabric.sdk.android.ActivityLifecycleManager
-keep class io.fabric.sdk.android.services.settings.AnalyticsSettingsData
-keep class io.fabric.sdk.android.services.concurrency.internal.RetryState
-keep class io.fabric.sdk.android.services.concurrency.internal.Backoff
-keep class io.fabric.sdk.android.services.common.Crash$LoggedException
-keep class io.fabric.sdk.android.services.common.Crash$FatalException

# Firebase Messaging
-dontwarn com.google.android.gms.measurement.AppMeasurement*
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzyv {
  <fields>;
}

# Firebase Analytics
-keepclassmembers class * extends com.google.android.gms.internal.measurement.zzyv {
  <fields>;
}