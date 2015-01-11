package paris;

import java.util.Date;

public class motor {
	private String ket = "";
	private String[]NOPOL = new String[5];
	public motor() {
		// TODO Auto-generated constructor stub
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}
	
	public void lihatData()
	{
		for (int x = 0 ; x < NOPOL.length; x++)
		{
			System.out.println(NOPOL[x]);
		}
	}
	
	public void masukkedatabase(String plat, int ke, String gate)
	{
		try{
			NOPOL[ke]= plat +" ; "+ gate +" ; "+new Date().toString();
			setKet("Tersimpan : "+NOPOL[ke]);
		}catch(Exception e)
		{
			setKet("Maaf Parkir Penuh!");
		}
	}
}

