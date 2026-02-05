//
//  iOSAppModule.swift
//  iosApp
//
//  Created by Hyago Henrique on 05/02/26.
//
import Shared
import Foundation

final class IosAppModule {
    let module: AppModule
    
    init() {
        let driverFactory = DatabaseDriverFactory()
        self.module = AppModule(driverFactory: driverFactory)
    }
}
