name := "practical_fp_workshop"

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "org.scalaz" %% "scalaz-concurrent" % "7.2.4"
)
