pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (
            url="https://jitpack.io"
        )
    }
//    versionCatalogs {
//        create("mylibs") {
//            from(files("gradle/mylibs.versions.toml"))
//        }
//    }
}
rootProject.name = "JetpackDemo"
include("app")
