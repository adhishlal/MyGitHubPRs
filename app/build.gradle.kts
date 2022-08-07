plugins {
    id("com.android.application")
    id("kotlin-kapt")
    kotlin("android")
}
android {
    compileSdk = Dependencies.Android.compileSdkVersion
    compileOptions.incremental = false
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdk = Dependencies.Android.minSdkVersion
        targetSdk = Dependencies.Android.targetSdkVersion
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName
        multiDexEnabled = true
        testInstrumentationRunner = Dependencies.TestLibs.androidTestRunner
    }
    buildTypes {

        release{
            isMinifyEnabled = true
            isDebuggable = false
            buildConfigField("String","BaseURL", "\"${Dependencies.BaseURLs.gitHubBaseURL}\"" )
            buildConfigField("String","AuthToken", "\"${Dependencies.Authorization.authToken}\"" )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String","BaseURL", "\"${Dependencies.BaseURLs.gitHubBaseURL}\"")
            buildConfigField("String","AuthToken", "\"${Dependencies.Authorization.authToken}\"" )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += listOf("stage")
}

kapt {
    generateStubs = true
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // kotlin and support libs
    implementation(Dependencies.Kotlin.kotlin_std)
    implementation(Dependencies.SupportLibs.appcompat)

    // material
    implementation(Dependencies.SupportLibs.material)

    // retrofit
    implementation(Dependencies.SupportLibs.retrofit)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // coroutines
    implementation(Dependencies.SupportLibs.coroutines)
    implementation(Dependencies.SupportLibs.coroutinesCore)

    // androidx
    implementation(Dependencies.SupportLibs.fragmentx)
    implementation(Dependencies.SupportLibs.activityx)

    // picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    // interceptor
    implementation(Dependencies.SupportLibs.loggingInterceptor)

    // material
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // paging
    implementation("androidx.paging:paging-runtime:3.1.1")

    // dagger2
    implementation(Dependencies.SupportLibs.dagger)
    kapt(Dependencies.SupportLibs.daggerCompiler)
    kapt(Dependencies.SupportLibs.daggerProcessor)
    implementation(Dependencies.SupportLibs.daggerAndroidSupport)

    testImplementation(Dependencies.TestLibs.junit)
    androidTestImplementation(Dependencies.TestLibs.runner)
    androidTestImplementation(Dependencies.TestLibs.espresso)
}