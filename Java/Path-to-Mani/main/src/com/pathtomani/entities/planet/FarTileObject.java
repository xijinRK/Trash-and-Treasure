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
import com.pathtomani.common.ManiMath;
import com.pathtomani.game.FarObj;
import com.pathtomani.game.ManiGame;
import com.pathtomani.game.ManiObject;

public class FarTileObject implements FarObj {
  private final Planet myPlanet;
  private final float myToPlanetAngle;
  private final float myDist;
  private final float mySize;
  private final Tile myTile;
  private final Vector2 myPos;
  private final float myRadius;

  public FarTileObject(Planet planet, float toPlanetAngle, float dist, float size, Tile tile) {
    myPlanet = planet;
    myToPlanetAngle = toPlanetAngle;
    myDist = dist;
    mySize = size;
    myRadius = ManiMath.sqrt(2) * mySize;
    myTile = tile;
    myPos = new Vector2();
  }

  @Override
  public boolean shouldBeRemoved(ManiGame game) {
    return false;
  }

  @Override
  public ManiObject toObj(ManiGame game) {
    return new TileObjBuilder().build(game, mySize, myToPlanetAngle, myDist, myTile, myPlanet);
  }

  @Override
  public void update(ManiGame game) {
    if (game.getPlanetMan().getNearestPlanet() == myPlanet) {
      ManiMath.fromAl(myPos, myPlanet.getAngle() + myToPlanetAngle, myDist);
      myPos.add(myPlanet.getPos());
    }
  }

  @Override
  public float getRadius() {
    return myRadius;
  }

  @Override
  public Vector2 getPos() {
    return myPos;
  }

  @Override
  public String toDebugString() {
    return null;
  }

  @Override
  public boolean hasBody() {
    return true;
  }

  public float getAngle() {
    return myPlanet.getAngle() + myToPlanetAngle + 90;
  }

  public Planet getPlanet() {
    return myPlanet;
  }


  public float getSz() {
    return mySize;
  }

  public Tile getTile() {
    return myTile;
  }
}
