//
//  RootHolder.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 21.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

class RootHolder: ObservableObject {
    let lifeCycle: LifecycleRegistry
    let root: RootComponent
    let snackBarManager: SnackBarManager
    
    init() {
        lifeCycle = LifecycleRegistryKt.LifecycleRegistry()
        snackBarManager = SnackBarManagerProvider().getSnackBarManager()
        root = SharedAppInitializer.shared.createRootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifeCycle),
            snackBarManager: snackBarManager
        )
    
        LifecycleRegistryExtKt.create(lifeCycle)
    }
    
    deinit {
        LifecycleRegistryExtKt.destroy(lifeCycle)
    }
}
