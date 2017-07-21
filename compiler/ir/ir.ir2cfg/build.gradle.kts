
apply { plugin("kotlin") }

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:ir.tree"))
}

configureKotlinProjectSourcesDefault()
configureKotlinProjectNoTests()

