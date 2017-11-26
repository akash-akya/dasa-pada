/*
 * vachana. An application for Android users, it contains kannada vachanas
 * Copyright (c) 2016. akash
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.akash.dasapada.dbUtil;

import java.io.Serializable;

public class KathruMini implements Serializable{
    private int id;
    private String name;
    private String ankitha;
    private int count;
    private int favorite;

    public KathruMini(int id, String name, String ankitha, int count, int favorite) {
        this.id = id;
        this.name = name;
        this.ankitha = ankitha;
        this.count = count;
        this.favorite = favorite;
    }

    public int getFavorite() {
        return favorite;
    }

    public String getAnkitha() {
        return ankitha;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setFavorite(boolean favorite) {
        if (favorite)
            this.favorite = 1;
        else
            this.favorite = 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
