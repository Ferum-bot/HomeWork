// add to main gradle file of project
ext.kotlin_version = '1.3.72'
ext.navigation_version = '2.2.0'

buildscript {
  dependencies {
        classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-rc02"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

// navigation safeargs
apply plugin: 'kotlin-kapt'
apply plugin: "androidx.navigation.safeargs.kotlin"

android {
  buildFeatures {
      dataBinding true
  }

  compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
       jvmTarget = JavaVersion.VERSION_1_8
  }
}

dependencies {
  // KTX
  implementation 'androidx.core:core-ktx:1.3.1'

  // Navigation
  implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
  implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"

  // Lifecycles
  implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
}
