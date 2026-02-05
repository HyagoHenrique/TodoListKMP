//
//  TodoViewModel.swift
//  iosApp
//
//  Created by Hyago Henrique on 05/02/26.
//

import SwiftUI
import Shared
import Combine

@MainActor
final class TodoViewModel: ObservableObject {
    @Published var items: [TodoItem] = []
    @Published var input: String = ""
    
    private let controller: TodoController
    private var subscription: SwiftFlowSubscription?
    
    init(controller: TodoController) {
        self.controller = controller
        
        subscription = SwiftFlowBridgeKt.watchFlow(
            flow: controller.state,
            onEach: { [weak self] value in
                guard let self else { return }
                let state = value as! TodoState
                self.items = state.items
                self.input = state.input
            }
        )
        
    }
    

    func onInputChange(_ text: String) {
        controller.onInputChange(text: text)
    }

    func addTodo() {
        controller.addTodo()
    }

    func toggleTodo(id: Int64) {
        controller.toggleTodo(id: id)
    }

    func deleteTodo(id: Int64) {
        controller.deleteTodo(id: id)
    }

    deinit {
        controller.close()
    }
}
