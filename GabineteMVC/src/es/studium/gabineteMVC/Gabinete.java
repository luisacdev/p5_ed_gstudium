package es.studium.gabineteMVC;

public class Gabinete
{
	public static void main(String[] args)
	{
		Vista Vista = new Vista();
		Modelo Modelo = new Modelo();
		new Controlador(Vista, Modelo);
	}
}