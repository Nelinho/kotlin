
apply { plugin("kotlin") }

dependencies {
    compile(project(":kotlin-stdlib"))
    compile(project(":compiler:util"))
    compile(project(":compiler:cli-common"))
    compile(project(":compiler:frontend.java"))
    compile(ideaSdkCoreDeps("intellij-core", "util"))
    buildVersion()
}

configureKotlinProjectSourcesDefault()
configureKotlinProjectNoTests()

fixKotlinTaskDependencies()
