
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

buildscript {
    repositories {
        mavenLocal()
        maven { setUrl(rootProject.extra["repo"]) }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlinVersion"]}")
    }
}

apply { plugin("kotlin") }

fun Project.fixKotlinTaskDependencies() {
    the<JavaPluginConvention>().sourceSets.all { sourceset ->
        val taskName = if (sourceset.name == "main") "classes" else (sourceset.name + "Classes")
        tasks.withType<Task> {
            if (name == taskName) {
                dependsOn("copy${sourceset.name.capitalize()}KotlinClasses")
            }
        }
    }
}

// TODO: common ^ 8< ----

configure<JavaPluginConvention> {
    sourceSets.getByName("main").apply {
        java.setSrcDirs(
                listOf("shared/src/main/kotlin",
                       "shared/src/main/kotlin.jvm",
                       "junit/src/main/kotlin")
                .map { File(projectDir, it) })
    }
    sourceSets.getByName("test").apply {
        java.setSrcDirs(emptyList<File>())
    }
}

dependencies {
    compile(project(":core.builtins"))
    compile("junit:junit:4.11")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xallow-kotlin-package", "-module-name", "kotlin-test")
}

tasks.withType<Jar> {
    exclude("kotlin/internal/OnlyInputTypes*", "kotlin/internal/InlineOnly*", "kotlin/internal")
}

fixKotlinTaskDependencies()
