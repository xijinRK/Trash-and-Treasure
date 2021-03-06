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

package com.pathtomani.menu;

import com.badlogic.gdx.graphics.Color;
import com.pathtomani.common.GameOptions;
import com.pathtomani.ManiApplication;
import com.pathtomani.common.ManiMath;
import com.pathtomani.ui.*;
import com.pathtomani.common.Const;
import com.pathtomani.gfx.ManiColor;

import java.util.ArrayList;
import java.util.List;

public class CreditsScreen implements ManiUiScreen {
  public static final float MAX_AWAIT = 6f;
  private final ArrayList<ManiUiControl> myControls;
  private final ManiUiControl myCloseCtrl;
  private final ArrayList<String> myPages;
  private final Color myColor;

  private int myIdx;
  private float myPerc;

  public CreditsScreen(MenuLayout menuLayout,float r, GameOptions gameOptions) {
    myControls = new ArrayList<ManiUiControl>();
    myCloseCtrl = new ManiUiControl(menuLayout.buttonRect(-1, 4), true, gameOptions.getKeyEscape());
    myCloseCtrl.setDisplayName("Back");
    myControls.add(myCloseCtrl);
    myColor = ManiColor.col(1, 1);

    myPages = new ArrayList<String>();
    String[][] sss = {
      {
        "A game from",
        "",
        "Burnt Game Productions"
      },
      {
        "Original Creators",
        "",
        "Idea, coding, team lead:",
        "Milosh Petrov",
        "",
        "Drawing:",
        "Kent C. Jensen",
        "",
        "Additional coding:",
        "Nika \"NoiseDoll\" Burimenko",
        "",
        "Additional drawing:",
        "Julia Nikolaeva"
      },
      {
        "Burnt Games Productions Team:",
        "",
        "Team Leader: CrazyWolf",
        "Programmer: stur86 & CrazyWolf",
        "Ideas: xXDahChubChubXx",
        "Ideas: Aro2220",
        "Music selection: KittoDJ",
        "Art: CrazyWolf"
      },
      {
        "MovingBlocks team on GitHub",
        "",
        "Cervator",
        "Rulasmur",
        "theotherjay",
        "LinusVanElswijk",
        "SimonC4",
        "grauerkoala",
        "rzats",
        "LadySerenaKitty",
        "askneller",
        "JGelfand",
        "CrazyWolf"
      },
      {
        "Music by",
        "",
        "Alan Walker - NCS",
        "Cartoon - NCS",
        "Prismo"
      },
      {
        "Game engine:",
        "LibGDX",
        "",
        "Windows wrapper:",
        "Launch4J"
      },
      {
        "Font:",
        "\"Jet Set\" by Captain Falcon",
        "",
        "Sounds by FreeSound.org users:",
        "Smokum, Mattpavone",
        "Hanstimm, Sonidor,",
        "Isaac200000, TheHadnot, Garzul",
        "Dpoggioli, Raremess, Giddykipper,",
        "Steveygos93",
      },
    };
    for (String[] ss : sss) {
      StringBuilder page = new StringBuilder();
      for (String s : ss) {
        page.append(s).append("\n");
      }
      myPages.add(page.toString());
    }

  }

  @Override
  public List<ManiUiControl> getControls() {
    return myControls;
  }

  @Override
  public void onAdd(ManiApplication cmp) {
    myIdx = 0;
    myPerc = 0;
    myColor.a = 0;
  }

  @Override
  public void updateCustom(ManiApplication cmp, ManiInputManager.Ptr[] ptrs, boolean clickedOutside) {
    if (myCloseCtrl.isJustOff()) {
      cmp.getInputMan().setScreen(cmp, cmp.getMenuScreens().main);
      return;
    }
    myPerc += Const.REAL_TIME_STEP / MAX_AWAIT;
    if (myPerc > 1) {
      myPerc = 0;
      myIdx++;
      if (myIdx >= myPages.size()) myIdx = 0;
    }
    float a = myPerc * 2;
    if (a > 1) a = 2 - a;
    a *= 3;
    myColor.a = ManiMath.clamp(a);
  }

  @Override
  public boolean isCursorOnBg(ManiInputManager.Ptr ptr) {
    return false;
  }

  @Override
  public void blurCustom(ManiApplication cmp) {
  }

  @Override
  public void drawBg(UiDrawer uiDrawer, ManiApplication cmp) {
  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, ManiApplication cmp) {
  }

  @Override
  public void drawText(UiDrawer uiDrawer, ManiApplication cmp) {
    uiDrawer.drawString(myPages.get(myIdx), uiDrawer.r/2, .5f, FontSize.MENU, true, myColor);
  }

  @Override
  public boolean reactsToClickOutside() {
    return false;
  }
}
