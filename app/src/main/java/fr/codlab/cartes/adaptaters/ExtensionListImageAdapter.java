package fr.codlab.cartes.adaptaters;

import fr.codlab.cartes.MainActivity;
import fr.codlab.cartes.util.Cache;
import fr.codlab.cartes.util.CardImageView;
import fr.codlab.cartes.util.Extension;
import fr.codlab.cartes.util.Language;
import fr.codlab.cartes.widget.Gallery3D;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ExtensionListImageAdapter extends BaseAdapter {
	int _background;
	private Context _context;

	//private ImageView[] mImages;
	private Extension _extension;
    private final Cache _cache;

	public ExtensionListImageAdapter(Context c, Extension extension) {
		_context = c;
		_extension = extension;
        _cache = new Cache();
	}



	public int getCount() {
		return _extension.getCount();
	}

	public Object getItem(int position) {
		return _extension.getCarte(position);
	}

	public long getItemId(int position) {
		return _extension.getCarte(position).getId();
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		//Use this code if you want to load from resources
		ImageView i = new ImageView(_context);
		try{
            int quality = _context.getSharedPreferences(MainActivity.PREFS,0).getInt("quality", 50);
			CardImageView.setBitmapToImageView(quality, _cache, position, i, _extension.getShortName(), _extension.getShortName()+"_"+_extension.getCarte(position).getCarteId()+( MainActivity.InUse == Language.FR ? "" : "_us"), true);
			i.setLayoutParams(new Gallery3D.LayoutParams(80, 130));
			i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 

			BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
			drawable.setAntiAlias(true);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return i;
	}
}