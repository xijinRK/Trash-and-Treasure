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

package com.pathtomani.entities.planet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.pathtomani.common.ManiMath;
import com.pathtomani.game.DmgType;
import com.pathtomani.game.FarObj;
import com.pathtomani.game.ManiGame;
import com.pathtomani.game.ManiObject;
import com.pathtomani.managers.dra.Dra;

import java.util.List;

public class PlanetSprites implements ManiObject {

  private final Planet myPlanet;
  private float myRelAngleToPlanet;
  private final float myDist;
  private final List<Dra> myDras;
  private final float myToPlanetRotSpd;
  private final Vector2 myPos;
  private float myAngle;

  public PlanetSprites(Planet planet, float relAngleToPlanet, float dist, List<Dra> dras, float toPlanetRotSpd) {
    myPlanet = planet;
    myRelAngleToPlanet = relAngleToPlanet;
    myDist = dist;
    myDras = dras;
    myToPlanetRotSpd = toPlanetRotSpd;
    myPos = new Vector2();
    setDependentParams();
  }

  @Override
  public void update(ManiGame game) {
    setDependentParams();
    myRelAngleToPlanet += myToPlanetRotSpd * game.getTimeStep();
  }

  private void setDependentParams() {
    float angleToPlanet = myPlanet.getAngle() + myRelAngleToPlanet;
    ManiMath.fromAl(myPos, angleToPlanet, myDist, true);
    myPos.add(myPlanet.getPos());
    myAngle = angleToPlanet + 90;
  }

  @Override
  public boolean shouldBeRemoved(ManiGame game) {
    return false;
  }

  @Override
  public void onRemove(ManiGame game) {
  }

  @Override
  public void receiveDmg(float dmg, ManiGame game, Vector2 pos, DmgType dmgType) {
  }

  @Override
  public boolean receivesGravity() {
    return false;
  }

  @Override
  public void receiveForce(Vector2 force, ManiGame game, boolean acc) {
  }

  @Override
  public Vector2 getPosition() {
    return myPos;
  }

  @Override
  public FarObj toFarObj() {
    return new FarPlanetSprites(myPlanet, myRelAngleToPlanet, myDist, myDras, myToPlanetRotSpd);
  }

  @Override
  public List<Dra> getDras() {
    return myDras;
  }

  @Override
  public float getAngle() {
    return myAngle;
  }

  @Override
  public Vector2 getSpd() {
    return null;
  }

  @Override
  public void handleContact(ManiObject other, ContactImpulse impulse, boolean isA, float absImpulse,
                            ManiGame game, Vector2 collPos)
  {
  }

  @Override
  public String toDebugString() {
    return null;
  }

  @Override
  public Boolean isMetal() {
    return false;
  }

  @Override
  public boolean hasBody() {
    return false;
  }

}