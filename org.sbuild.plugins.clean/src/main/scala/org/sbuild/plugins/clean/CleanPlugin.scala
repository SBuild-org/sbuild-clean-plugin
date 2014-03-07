package org.sbuild.plugins.clean

import de.tototec.sbuild._
import de.tototec.sbuild.addons.java.{ Javac => JavacAddon }

class CleanPlugin(implicit project: Project) extends Plugin[Clean] {

  def create(name: String): Clean = name match {
    case "" => Clean(dirs = Seq(Path("target")), evictCache = true, targetName = "clean")
    case name => Clean(dirs = Seq(Path(name)), evictCache = false, targetName = s"clean-$name")
  }

  def applyToProject(instances: Seq[(String, Clean)]): Unit = instances.foreach {
    case (name, clean) =>
      val cleanTarget = Target("phony:" + clean.targetName) dependsOn clean.dependsOn exec {
        clean.files.distinct.foreach { file => file.deleteFile }
        clean.dirs.distinct.foreach { dir => dir.deleteRecursive }
      }
      if (clean.evictCache) cleanTarget.evictCache
  }

}