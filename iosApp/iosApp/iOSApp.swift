import SwiftUI

@main
struct iOSApp: App {
    private let iosModule = IosAppModule()
    var body: some Scene {
        WindowGroup {
            TodoScreen(controller: iosModule.module.todoController)
        }
    }
}
