package net.yscs.android.stromundspannung;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle drawerToggle;

	private CharSequence drawerTitle;
	private CharSequence title;
	public static String[] partTitle, descriptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		title = drawerTitle = getTitle();
		partTitle = getResources().getStringArray(R.array.drawer_titel);
		descriptions = getResources().getStringArray(R.array.drawer_descriptions);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListView = (ListView) findViewById(R.id.left_drawer);

		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		BaseAdapter adapter = new BaseAdapter() {

			private int title_pos_1 = 0;
			private int title_pos_2 = 4;
			private int title_pos_3 = 8;
			private final Integer LIST_HEADER = 0;
			private final Integer LIST_ITEM = 1;

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				String headerText = getHeader(position);
				if (headerText != null) {

					View item = convertView;
					if (convertView == null
							|| convertView.getTag() == LIST_ITEM) {

						item = LayoutInflater.from(getApplicationContext())
								.inflate(R.layout.lv_header_layout, parent,
										false);
						item.setTag(LIST_HEADER);

					}

					TextView headerTextView = (TextView) item
							.findViewById(R.id.lv_list_hdr);
					headerTextView.setText(headerText);
					return item;
				}

				View item = convertView;
				if (convertView == null || convertView.getTag() == LIST_HEADER) {
					item = LayoutInflater.from(getApplicationContext())
							.inflate(R.layout.lv_layout, parent, false);
					item.setTag(LIST_ITEM);
				}

				TextView header = (TextView) item
						.findViewById(R.id.lv_item_header);
				header.setText(partTitle[position % partTitle.length]);

				TextView subtext = (TextView) item
						.findViewById(R.id.lv_item_subtext);
				subtext.setText(descriptions[position % descriptions.length]);

				return item;

			}

			private String getHeader(int position) {

				if (position == title_pos_1 || position == title_pos_2
						|| position == title_pos_3) {
					return partTitle[position];
				}

				return null;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public int getCount() {
				return 11;
			}
		};
		drawerListView.setAdapter(adapter);
		drawerListView.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(drawerTitle);
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

		if (savedInstanceState == null) {
			selectItem(1);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return true;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if ((position != 0) && (position != 4) && (position != 8)) {
				selectItem(position);
			}
		}
	}

	private void selectItem(int position) {
		Fragment fragment = null;
		switch (position) {
		case 1:
			fragment = new OhmschesGesetzStrom();
			break;
		case 2:
			fragment = new OhmschesGesetzSpannung();
			break;
		case 3:
			fragment = new OhmschesGesetzWiderstand();
			break;
		case 5:
			fragment = new ErsatzRTwo();
			break;
		case 6:
			fragment = new ErsatzRGesamt();
			break;
		case 7:
			fragment = new ErsatzRGesamtRn();
			break;
		case 9:
			fragment = new WiderstandElLeiter();
			break;
		case 10:
			fragment = new WiderstandsaenderungTemp();
			break;
		default:
			fragment = new OhmschesGesetzStrom();
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			drawerListView.setItemChecked(position, true);
			setTitle(partTitle[position]);
			drawerLayout.closeDrawer(drawerListView);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
}