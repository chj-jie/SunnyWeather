pluginManagement {
    repositories {
        // 阿里云 Gradle 插件镜像
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        // 阿里云 Google 镜像
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        // 阿里云中央仓库镜像
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        // 阿里云公共仓库
        maven { url = uri("https://maven.aliyun.com/repository/public") }

        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 阿里云镜像源
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://jitpack.io") }

        google()
        mavenCentral()
    }
}

rootProject.name = "SunnyWeather"
include(":app")