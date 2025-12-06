###############
## HILT / DAGGER
###############
# Keep Hilt-generated classes
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponentManager { *; }
-keep class * extends dagger.hilt.internal.ComponentEntryPoint { *; }

# Keep Injected constructors
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
}

# Prevent stripping annotations
-keepattributes *Annotation*


###############
## ROOM DATABASE
###############
# Keep Entities, Dao, Database
-keep class androidx.room.** { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Entity class * { *; }
-keep class * extends androidx.room.RoomDatabase { *; }

# Keep Kotlin metadata for Room
-keepattributes Signature, InnerClasses, EnclosingMethod, KotlinMetadata


###############
## GSON
###############
# Keep model classes (Gson uses reflection)
-keep class com.google.gson.** { *; }
-keep class * implements java.io.Serializable { *; }
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep fields with SerializedName
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}


###############
## ZXING (QR / Barcode)
###############
-keep class com.google.zxing.** { *; }
-keep class com.journeyapps.** { *; }

# Avoid warnings
-dontwarn com.google.zxing.**
-dontwarn com.journeyapps.**


###############
## WORKMANAGER
###############
-keep class androidx.work.** { *; }
-keep class * extends androidx.work.ListenableWorker { *; }

-dontwarn androidx.work.**


###############
## LOTTIE
###############
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**


###############
## NAVIGATION COMPOSE
###############
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**


###############
## ANDROIDX / COMPOSE
###############
# Keep Compose runtime
-keep class androidx.compose.** { *; }

# Keep Kotlin metadata (VERY IMPORTANT)
-keepattributes SourceFile, LineNumberTable, EnclosingMethod, EnclosingClass, InnerClasses, Signature


###############
## MATERIAL 3
###############
-keep class androidx.compose.material3.** { *; }
-dontwarn androidx.compose.material3.**


###############
## MULTIDEX
###############
-keep class androidx.multidex.** { *; }


###############
## GSON (Bitmap in Entity)
###############
# Prevent Bitmap being stripped
-keep class android.graphics.** { *; }


###############
## KOTLIN COROUTINES
###############
-dontwarn kotlinx.coroutines.**


###############
## SAFE DEFAULTS
###############
-dontoptimize
-dontpreverify
-dontwarn org.jetbrains.annotations.**

##########################
## IGNORE XR MISSING CLASSES
##########################
# XR packages used by androidx.xr.compose.material3
-dontwarn com.android.extensions.xr.**
-dontwarn com.google.androidxr.**
-dontwarn com.google.imp.splitengine.**
-dontwarn com.android.extensions.xr.node.**
-dontwarn com.android.extensions.xr.splitengine.**
-dontwarn com.android.extensions.xr.subspace.**
-dontwarn com.android.extensions.xr.function.**

# Keep XR Compose Material3 to avoid being removed
-keep class androidx.xr.compose.material3.** { *; }