/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 * @author Karen
 */
public class VirtualController
{

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean hitting; 

    public boolean isLeft()
    {
        return left;
    }

    public void setLeft(boolean left)
    {
        this.left = left;
    }

    public boolean isRight()
    {
        return right;
    }

    public void setRight(boolean right)
    {
        this.right = right;
    }

    public boolean isUp()
    {
        return up;
    }

    public void setUp(boolean up)
    {
        this.up = up;
    }

    public boolean isHitting() 
    { 
        return hitting; 
    } 
 
    public void setHitting(boolean hitting) 
    { 
        this.hitting = hitting; 
    } 
}
