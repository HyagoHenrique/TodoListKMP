//
//  TodoScreen.swift
//  iosApp
//
//  Created by Hyago Henrique on 05/02/26.
//

import SwiftUI
import Shared

struct TodoScreen: View {

    @StateObject private var viewModel: TodoViewModel

    init(controller: TodoController) {
        _viewModel = StateObject(wrappedValue: TodoViewModel(controller: controller))
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 12) {

                HStack(spacing: 8) {
                    TextField("Nova tarefa...", text: Binding(
                        get: { viewModel.input },
                        set: { viewModel.onInputChange($0) }
                    ))
                    .textFieldStyle(.roundedBorder)

                    Button("Adicionar") {
                        viewModel.addTodo()
                    }
                    .buttonStyle(.borderedProminent)
                }
                .padding(.horizontal)

                List {
                    ForEach(viewModel.items, id: \.id) { item in
                        HStack {
                            Image(systemName: item.isDone ? "checkmark.circle.fill" : "circle")
                                .onTapGesture {
                                    viewModel.toggleTodo(id: item.id)
                                }

                            Text(item.title)
                                .strikethrough(item.isDone)
                            
                            Spacer()
                            
                            Button {
                                viewModel.deleteTodo(id: item.id)
                            } label: {
                                Image(systemName: "trash")
                                    .foregroundColor(.red)
                            }
                            .buttonStyle(.borderless)

                        }
                        .contentShape(Rectangle())
                        .onTapGesture {
                            viewModel.toggleTodo(id: item.id)
                        }
                    }
                    .onDelete { indexSet in
                        withAnimation(.easeInOut(duration: 1.5)) {
                            for index in indexSet {
                                let id = viewModel.items[index].id
                                viewModel.deleteTodo(id: id)
                            }
                        }
                    }
                }
            }
            .navigationTitle("Todo")
        }
    }
}
