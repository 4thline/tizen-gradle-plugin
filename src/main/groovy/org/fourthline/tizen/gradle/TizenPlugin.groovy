/*
 * Copyright (C) 2015 4th Line GmbH, Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.fourthline.tizen.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class TizenPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        if (project.extensions.findByName("tizen") == null) {
            project.extensions.create("tizen", Tizen)
        }

        project.task("tizenGenerate", type: TizenGenerate)

        project.task("tizenQuery", type: TizenQueryTask)

        project.task("tizenBuild", type: TizenBuild)

        project.task("tizenSigning", type: TizenSigning).dependsOn("tizenBuild")

        project.task("tizenPackaging", type: TizenPackaging).dependsOn("tizenSigning")

        project.task("tizenInstall", type: TizenInstall)

        project.task("tizenUninstall", type: TizenUninstall)

        project.task("tizenRun", type: TizenRun)

        project.task("tizenDebug", type: TizenDebug)
    }

}
