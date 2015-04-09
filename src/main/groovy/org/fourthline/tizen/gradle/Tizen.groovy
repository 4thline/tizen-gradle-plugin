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

import org.gradle.util.ConfigureUtil

class Tizen {

    String sdkPath
    String device
    String logLevel
    boolean quiet
    String applicationId
    int sdbTimeoutSeconds = 10

    class Generate {
        String name
        String path
    }

    Generate generate = new Generate()

    def generate(Closure closure) {
        ConfigureUtil.configure(closure, generate, false)
    }

    class Build {
        String input
        String output
        String exclude
        boolean optimize = false
        boolean excludeUIFramework
        boolean excludeUIFrameworkMinimized

    }

    Build build = new Build()

    def build(Closure closure) {
        ConfigureUtil.configure(closure, build, false)
    }

    class Signing {
        String profile
        String profilesXml
        String input
        boolean nocheck = true
    }

    Signing signing = new Signing()

    def signing(Closure closure) {
        ConfigureUtil.configure(closure, signing, false)
    }

    class Packaging {
        String input
        String output
        boolean nocheck = true
        boolean overwrite = true
    }

    Packaging packaging = new Packaging()

    def packaging(Closure closure) {
        ConfigureUtil.configure(closure, packaging, false)
    }

    class Install {
        String widget
    }

    Install install = new Install()

    def install(Closure closure) {
        ConfigureUtil.configure(closure, install, false)
    }

}
