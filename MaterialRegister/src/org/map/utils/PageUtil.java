package org.map.utils;

import javafx.scene.Node;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.TextBuilder;

import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.view.AddHeatChart;
import org.map.view.AddMaterial;
import org.map.view.AddUser;
import org.map.view.AddValidation;
import org.map.view.ChangePassword;
import org.map.view.EditHeatChart;
import org.map.view.EditMaterial;
import org.map.view.EditUser;
import org.map.view.EditValidation;
import org.map.view.Loading;
import org.map.view.Settings;
import org.map.view.ViewHeatChart;
import org.map.view.ViewMaterial;
import org.map.view.ViewReport;
import org.map.view.ViewUser;
import org.map.view.ViewValidation;

public class PageUtil {

	private static Loading loadingPage = new Loading();

	public static Node getLoadingPageView() {

		return loadingPage;
	}

	public static Node getPageView(String pageName, String ctNumber) {
		ViewMaterial pageView = new ViewMaterial();
		
		if (ctNumber != null) {
			MaterialMaster master = MaterialData.getMaterialDetails(ctNumber);
			pageView.createViewTab(master);
		}
		
		return pageView;
	}
	
	public static Node getPageView(String pageName) {

		if (pageName.equalsIgnoreCase("Add User")) {
			AddUser pageView = new AddUser();
			return pageView;
		} else if (pageName.equalsIgnoreCase("View User")) {
			ViewUser pageView = new ViewUser();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Edit User")) {
			EditUser pageView = new EditUser();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Add Material")) {
			AddMaterial pageView = new AddMaterial();
			return pageView;
		} else if (pageName.equalsIgnoreCase("View Material")) {
			ViewMaterial pageView = new ViewMaterial();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Edit Material")) {
			EditMaterial pageView = new EditMaterial();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Add Validation")) {
			AddValidation pageView = new AddValidation();
			return pageView;
		} else if (pageName.equalsIgnoreCase("View Validation")) {
			ViewValidation pageView = new ViewValidation();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Edit Validation")) {
			EditValidation pageView = new EditValidation();
			return pageView;
		} else if (pageName.equalsIgnoreCase("View Material Register")) {
			ViewReport pageView = new ViewReport();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Add Heat Chart")) {
			AddHeatChart pageView = new AddHeatChart();
			return pageView;
		} else if (pageName.equalsIgnoreCase("View Heat Chart")) {
			ViewHeatChart pageView = new ViewHeatChart();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Edit Heat Chart")) {
			EditHeatChart pageView = new EditHeatChart();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Change Password")) {
			ChangePassword pageView = new ChangePassword();
			return pageView;
		} else if (pageName.equalsIgnoreCase("Settings")) {
			Settings pageView = new Settings();
			return pageView;
		} else {
			return VBoxBuilder
					.create()
					.children(
							TextBuilder.create().text("View does not exist")
									.build()).build();
		}
	}
}
