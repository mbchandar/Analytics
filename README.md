# Analytics
Sample project showing how to link Google and Firebase Analytics using a single Analytics class.

### Add all dependencies to build.gradle file

```
apply plugin: 'com.android.application'
android {
    compileSdkVersion 24
    buildToolsVersion "24.0.0"

    defaultConfig {
        applicationId "com.github.tomaszmartin.analytics"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.1.0'
    compile 'com.google.android.gms:play-services-analytics:9.2.1'
    compile 'com.google.firebase:firebase-core:9.2.1'
}
apply plugin: 'com.google.gms.google-services'
```
### Add google-services.json file

Download google-services.json file from Firebase.

### Add tracker.xml file
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- End current session if app sleeps for a period of time -->
    <integer name="ga_sessionTimeout">300</integer>

    <!-- Enable automatic Activity measurement -->
    <bool name="ga_autoActivityTracking">false</bool>

    <!--  The property id associated with this analytics tracker -->
    <string name="ga_trackingId">UA-XXXXXXXX-X</string>

</resources>
```
