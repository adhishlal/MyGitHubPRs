import Dependencies.Versions.activityXVersion
import Dependencies.Versions.androidGradleVersion
import Dependencies.Versions.appCompatVersion
import Dependencies.Versions.constraintLayoutVersion
import Dependencies.Versions.coroutineVersion
import Dependencies.Versions.daggerVersion
import Dependencies.Versions.espressoVersion
import Dependencies.Versions.fragmentXVersion
import Dependencies.Versions.junitVersion
import Dependencies.Versions.kotlinVersion
import Dependencies.Versions.materialVersion
import Dependencies.Versions.picassoVersion
import Dependencies.Versions.retrofitVersion
import Dependencies.Versions.runnerVersion

object Dependencies {

    object Android {
        const val minSdkVersion = 24
        const val targetSdkVersion = 31
        const val compileSdkVersion = 31
        const val applicationId = "com.adhishlal.mygithubprs"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object BaseURLs {
        const val gitHubBaseURL = "https://api.github.com/"
    }

    object Authorization {
        const val authToken = "token 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"
    }

    object Versions {
        const val kotlinVersion = "1.7.10"
        const val androidGradleVersion = "7.2.1"

        //test libs
        const val junitVersion = "4.12"
        const val runnerVersion = "1.1.0"
        const val espressoVersion = "3.1.0"

        const val appCompatVersion = "1.3.1"

        // material library
        const val materialVersion = "1.6.1"

        // retrofit version
        const val retrofitVersion = "2.9.0"

        // dagger version
        const val daggerVersion = "2.21"

        // coroutine version
        const val coroutineVersion = "1.6.4"

        // androidx version
        const val fragmentXVersion = "1.5.1"
        const val activityXVersion = "1.5.1"

        // picasso version
        const val picassoVersion = "2.71828"

        // constraint layout version
        const val constraintLayoutVersion = "2.1.4"
    }

    object Kotlin {
        val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object SupportLibs {
        const val appcompat = "androidx.appcompat:appcompat:$appCompatVersion"

        // material library
        const val material = "com.google.android.material:material:$materialVersion"

        // constraint layout
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        // retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitConvertor = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofitScalars = "com.squareup.retrofit2:converter-scalars:$retrofitVersion"
        const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

        // interceptor
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.9.0"

        // dagger2
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"

        // coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"

        // androidx
        const val fragmentx = "androidx.fragment:fragment-ktx:$fragmentXVersion"
        const val activityx = "androidx.activity:activity-ktx:$activityXVersion"

        // picasso
        const val picasso = "com.squareup.picasso:picasso:$picassoVersion"
    }

    object TestLibs {
        const val junit = "junit:junit:$junitVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val runner = "androidx.test:runner:$runnerVersion"
        const val androidTestRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
}