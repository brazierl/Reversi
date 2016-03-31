/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Objects;

/**
 *
 * @author p1509283
 */
public class Player {
    private int score;
    private String name;
    private int number;
    private boolean isHuman;

    public Player(String name, int number, boolean isHuman) {
        this.score = 0;
        this.name = name;
        this.number = number;
        this.isHuman = isHuman;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.score != other.score) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (this.isHuman != other.isHuman) {
            return false;
        }
        return true;
    }
    
    
    
    
}
