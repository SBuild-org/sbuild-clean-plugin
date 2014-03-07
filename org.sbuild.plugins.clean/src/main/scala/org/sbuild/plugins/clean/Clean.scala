package org.sbuild.plugins.clean

import java.io.File

import de.tototec.sbuild.TargetRefs

/**
 * Configuration for the SBuild Clean Plugin.
 *
 * @param targetName The name of the created clean target.
 * @param dirs Directories (or files) to be deleted recursivly.
 * @param files Files to be deleted.
 * @param evictCache If `true` this target will set the `[[Target#evictCache]]` property.
 * @param dependsOn Additional dependencies of the created clean target (contributes to the `[[Target#dependsOn]]` of the created target).
 */
case class Clean(
    targetName: String,
    dirs: Seq[File] = Seq(),
    files: Seq[File] = Seq(),
    evictCache: Boolean = false,
    dependsOn: TargetRefs = Seq()) {

  def addDirs(dirs: File*): Clean = copy(dirs = this.dirs ++ dirs)
  def addFiles(files: File*): Clean = copy(files = this.files ++ files)
  def addDependsOn(dependsOn: TargetRefs): Clean = copy(dependsOn = this.dependsOn ~ dependsOn)

  override def toString(): String = getClass().getSimpleName() +
    "(targetName=" + targetName +
    ",dirs=" + dirs +
    ",files=" + files +
    ",evictCache=" + evictCache +
    ",dependsOn=" + dependsOn +
    ")"
}
