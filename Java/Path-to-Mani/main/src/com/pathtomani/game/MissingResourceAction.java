/*
 * Copyright 2016 BurntGameProductions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pathtomani.game;

import com.pathtomani.ui.DebugCollector;

public enum MissingResourceAction {
  IGNORE("ignore"), WARN("warn"), FAIL("fail");
  public final String name;

  MissingResourceAction(String name) {
    this.name = name;
  }

  public static MissingResourceAction forName(String name) {
    for (MissingResourceAction dt : MissingResourceAction.values()) {
      if (dt.name.equals(name)) return dt;
    }
    throw new AssertionError("no missing resource action for name " + name);
  }

  public void handle(String msg) {
    if (this == WARN) DebugCollector.warn(msg);
    if (this == FAIL) throw new AssertionError(msg);
  }
}
