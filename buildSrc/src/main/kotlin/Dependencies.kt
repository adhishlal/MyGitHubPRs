import Dependencies.Versions.activityXVersion
import Dependencies.Versions.androidGradleVersion
import Dependencies.Versions.appCompatVersion
import Dependencies.Versions.coroutineVersion
import Dependencies.Versions.daggerVersion
import Dependencies.Versions.espressoVersion
import Dependencies.Versions.fragmentXVersion
import Dependencies.Versions.junitVersion
import Dependencies.Versions.kotlinVersion
import Dependencies.Versions.materialVersion
import Dependencies.Versions.retrofitVersion
import Dependencies.Versions.runnerVersion

object Dependencies {

    object Android {
        val minSdkVersion = 24
        val targetSdkVersion = 31
        val compileSdkVersion = 31
        val applicationId = "com.adhishlal.mygithubprs"
        val versionCode = 1
        val versionName = "1.0.0"
    }

    object BaseURLs {
        val gitHubBaseURL = "https://api.github.com/"
    }

    object Authorization {
        val authToken = "token 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"
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
    }

    object Kotlin {
        val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object SupportLibs {
        val appcompat = "androidx.appcompat:appcompat:$appCompatVersion"

        // material library
        val material = "com.google.android.material:material:$materialVersion"

        // retrofit
        val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"

        // interceptor
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.9.0"

        // dagger2
        val dagger = "com.google.dagger:dagger:$daggerVersion"
        val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
        val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"

        // coroutines
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"

        // androidx
        val fragmentx = "androidx.fragment:fragment-ktx:$fragmentXVersion"
        val activityx = "androidx.activity:activity-ktx:$activityXVersion"
    }

    object TestLibs {
        val junit = "junit:junit:$junitVersion"
        val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        val runner = "androidx.test:runner:$runnerVersion"
        val androidTestRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
}