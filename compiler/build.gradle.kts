
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

apply { plugin("kotlin") }

dependencies {
//    compile(project(":prepare:runtime", configuration = "default"))
//    compile(project(":kotlin-test:kotlin-test-jvm"))
//    compile(project(":prepare:reflect", configuration = "default"))
//    compile(project(":core:script.runtime"))
    compile(project(":core:util.runtime"))
    compile(project(":compiler:util"))
    compile(project(":compiler:container"))
    compile(project(":compiler:resolution"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:frontend.java"))
    compile(project(":compiler:frontend.script"))
    compile(project(":compiler:cli.cli-common"))
    compile(project(":compiler:daemon.daemon-common"))
    compile(project(":build-common"))
    compile(ideaSdkCoreDeps(*(rootProject.extra["ideaCoreSdkJars"] as Array<String>)))
    compile(commonDep("com.google.protobuf:protobuf-java"))
//    compile(fileTree(mapOf("dir" to "$rootDir/lib", "include" to "*.jar"))) // direct references below
    compile(commonDep("javax.inject"))
    compile(commonDep("com.google.code.findbugs", "jsr305"))
    compile(commonDep("com.github.spullara.cli-parser", "cli-parser"))
    compile(commonDep("org.fusesource.jansi", "jansi"))
    compile(commonDep("io.javaslang","javaslang"))
    compile(commonDep("jline"))
    compile(ideaSdkDeps("jps-model.jar", subdir = "jps"))
    compile(kotlinDep("stdlib"))
    compile(kotlinDep("script-runtime"))
    compile(kotlinDep("reflect"))
}

configure<JavaPluginConvention> {
    sourceSets.getByName("main").apply {
        listOf( "compiler/backend/src",
                "compiler/backend-common/src",
                "compiler/ir/backend.common/src",
                "compiler/ir/backend.jvm/src",
                "compiler/ir/ir.psi2ir/src",
                "compiler/ir/ir.tree/src",
                "compiler/light-classes/src",
                "compiler/plugin-api/src",
                "compiler/serialization/src",
                "js/js.ast/src",
                "js/js.translator/src",
                "js/js.frontend/src",
                "js/js.inliner/src",
                "js/js.parser/src",
                "js/js.serializer/src")
        .map { File(rootDir, it) }
        .let { java.setSrcDirs(it) }
//        println(compileClasspath.joinToString("\n    ", prefix = "classpath =\n    ") { it.canonicalFile.relativeTo(rootDir).path })
    }
    sourceSets.getByName("test").apply {
        java.setSrcDirs(emptyList<File>())
    }
}

fixKotlinTaskDependencies()

//tasks.withType<Jar> {
//    enabled = false
//}
