
// Have to stay in the separate dir: attempt to move it to the source root dir lead to invalid import into IDEA

apply { plugin("kotlin") }

dependencies {
    val compile by configurations
    compile(project(":core"))
    compile(project(":core::util.runtime"))
    compile(project(":compiler:util"))
    compile(project(":compiler:backend"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:frontend.java"))
    compile(project(":compiler:util"))
    compile(project(":compiler:cli-common"))
    compile(project(":compiler:cli"))
    compile(project(":compiler:light-classes"))
    compile(project(":compiler:serialization"))
    compile(project(":compiler:preloader"))
    compile(project(":compiler:daemon-common"))
    compile(project(":compiler:daemon-client"))
    compile(project(":js:js.serializer"))
    compile(project(":js:js.frontend"))
    compile(project(":js:js.translator"))
    compile(project(":plugins:android-extensions-compiler"))
    compile(kotlinDep("test"))
    compile(commonDep("junit"))
    compile(ideaSdkCoreDeps("intellij-core"))
    compile(ideaSdkDeps("openapi", "idea", "idea_rt"))
    compile(preloadedDeps("dx", subdir = "android-5.0/lib"))
}

configureKotlinProjectSources("tests-common", sourcesBaseDir = File(rootDir, "compiler"))
configureKotlinProjectNoTests()

