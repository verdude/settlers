package shared.definitions;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

public enum CatanColor
{
	@SerializedName("red")
	RED,
	@SerializedName("orange")
	ORANGE,
	@SerializedName("yellow")
	YELLOW,
	@SerializedName("blue")
	BLUE,
	@SerializedName("green")
	GREEN,
	@SerializedName("purple")
	PURPLE,
	@SerializedName("puce")
	PUCE,
	@SerializedName("white")
	WHITE,
	@SerializedName("brown")
	BROWN;
	
	private Color color;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
	}
	
	public Color getJavaColor()
	{
		return color;
	}
	public CatanColor fromString(String color){
		switch (color){
			case "red":
				return CatanColor.RED;
			case "orange":
				return CatanColor.ORANGE;
			case "yellow":
				return CatanColor.YELLOW;
			case "blue":
				return CatanColor.BLUE;
			case "green":
				return CatanColor.GREEN;
			case "purple":
				return CatanColor.PURPLE;
			case "puce":
				return CatanColor.PUCE;
			case "white":
				return CatanColor.WHITE;
			case "brown":
				return CatanColor.BROWN;
			default:
				return null;

		}
	}
}

