pluginManagement {
    repositories {
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
        maven {
            credentials {
                // enter the creds
                username = ""
                password = ""
            }
            url = uri("https://maven.pkg.github.com/JioMeet/JioLocalizationSDK_Android/")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "JioLocalizationSDKAndroid"
include(":app")
