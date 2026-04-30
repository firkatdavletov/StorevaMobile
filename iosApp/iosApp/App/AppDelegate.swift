//
//  AppDelegate.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 21.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import YandexMapsMobile
import Shared

class AppDelegate: NSObject, UIApplicationDelegate {
    lazy var rootHolder = RootHolder()
    
    override init() {
        SharedAppInitializer.shared.doInitKoin()
    }
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        YMKMapKit.setApiKey("api_key")
        YMKMapKit.sharedInstance()
        return true
    }
    
    func application(_ application: UIApplication, continue userActivity: NSUserActivity, restorationHandler: @escaping ([UIUserActivityRestoring]?) -> Void) -> Bool {
        return true
    }

    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        return true
    }

}
