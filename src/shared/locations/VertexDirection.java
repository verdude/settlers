package shared.locations;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}



	@Override
	public String toString() {
		switch(this) {
			case West: return "W";
			case NorthWest: return "NW";
			case NorthEast: return "NE";
			case East: return "E";
			case SouthEast: return "SE";
			case SouthWest: return "SW";
			default: throw new IllegalArgumentException();
		}
	}

	public VertexDirection fromString(String string){
		switch(string){
			case "NW":
				return NorthWest;
			case "W":
				return West;
			case "NE":
				return NorthEast;
			case "SE":
				return SouthEast;
			case "E":
				return East;
			case "SW":
				return SouthWest;
			default:
				return null;


		}

	}
}

