package shared.locations;

import com.google.gson.annotations.SerializedName;

public enum EdgeDirection
{

	@SerializedName("NW")
	NorthWest,
	@SerializedName("N")
	North,
	@SerializedName("NE")
	NorthEast,
	@SerializedName("SE")
	SouthEast,
	@SerializedName("S")
	South,
	@SerializedName("SW")
	SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	@Override
	public String toString() {
		switch(this) {
			case NorthWest: return "NW";
			case North: return "N";
			case NorthEast: return "NE";
			case SouthEast: return "SE";
			case South: return "S";
			case SouthWest: return "SW";
			default: throw new IllegalArgumentException();
		}
	}

	public EdgeDirection fromString(String string){
		switch(string){
			case "NW":
				return NorthWest;
			case "N":
				return North;
			case "NE":
				return NorthEast;
			case "SE":
				return SouthEast;
			case "S":
				return South;
			case "SW":
				return SouthWest;
			default:
				return null;


		}

	}
}

