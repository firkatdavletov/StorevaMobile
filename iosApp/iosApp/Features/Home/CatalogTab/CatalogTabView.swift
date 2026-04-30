//
//  CatalogTabView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 30/04/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CatalogTabView: View {
    let component: CatalogTabComponent
    
    init(component: CatalogTabComponent) {
        self.component = component
    }
    
    var body: some View {
        Text("Catalog Tab!")
    }
}
