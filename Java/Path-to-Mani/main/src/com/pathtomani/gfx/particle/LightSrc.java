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

package com.pathtomani.gfx.particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.pathtomani.managers.dra.Dra;
import com.pathtomani.gfx.ManiColorUtil;
import com.pathtomani.common.ManiMath;
import com.pathtomani.game.ManiGame;
import com.pathtomani.managers.dra.DraLevel;
import com.pathtomani.managers.dra.RectSprite;

import java.util.List;

public class LightSrc {
  public static final float DEFAULT_FADE_TIME = .1f;
  public static final float A_RATIO = .5f;
  public static final float SZ_RATIO = .8f;

  private final RectSprite myCircle;
  private final RectSprite myHalo;
  private final float mySz;
  private float myWorkPerc;
  private final float myIntensity;
  private float myFadeTime;

  /** doesn't consume relPos
   */
  public LightSrc(ManiGame game, float sz, boolean hasHalo, float intensity, Vector2 relPos, Color col) {
    TextureAtlas.AtlasRegion tex = game.getTexMan().getTex("smallGameObjs/particles/lightCircle", null);
    mySz = sz;
    myCircle = new RectSprite(tex, 0, 0, 0, new Vector2(relPos), DraLevel.PART_BG_0, 0, 0, col, true);
    tex = game.getTexMan().getTex("smallGameObjs/particles/lightHalo", null);
    if (hasHalo) {
      Color haloCol = new Color(col);
      ManiColorUtil.changeBrightness(haloCol, .8f);
      myHalo = new RectSprite(tex, 0, 0, 0, new Vector2(relPos), DraLevel.PART_FG_0, 0, 0, haloCol, true);
    } else {
      myHalo = null;
    }
    myIntensity = intensity;
    myFadeTime = DEFAULT_FADE_TIME;
  }

  public void update(boolean working, float baseAngle, ManiGame game) {
    if (working) {
      myWorkPerc = 1f;
    } else {
      myWorkPerc = ManiMath.approach(myWorkPerc, 0, game.getTimeStep() / myFadeTime);
    }
    float baseA = ManiMath.rnd(.5f, 1) * myWorkPerc * myIntensity;
    myCircle.tint.a = baseA * A_RATIO;
    float sz = (1 + ManiMath.rnd(.2f * myIntensity)) * mySz;
    myCircle.setTexSz(SZ_RATIO * sz);
    if (myHalo != null) {
      myHalo.tint.a = baseA;
      myHalo.relAngle = game.getCam().getAngle() - baseAngle;
      myHalo.setTexSz(sz);
    }
  }

  public boolean isFinished() {
    return myWorkPerc <= 0;
  }

  public void collectDras(List<Dra> dras) {
    dras.add(myCircle);
    if (myHalo != null) dras.add(myHalo);
  }

  public void setFadeTime(float fadeTime) {
    myFadeTime = fadeTime;
  }

  public void setWorking() {
    myWorkPerc = 1;
  }

  public void setRelPos(Vector2 relPos) {
    myCircle.relPos.set(relPos);
    if (myHalo != null) myHalo.relPos.set(relPos);
  }
}
