//
//  RemoteImage.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 11/02/2026.
//  Copyright © 2026 orgName. All rights reserved.
//

import SwiftUI

struct RemoteImage: View {
    let urlString: String?

    private var imageURL: URL? {
        urlString.flatMap(URL.init(string:))
    }

    var body: some View {
        Group {
            if let imageURL {
                AsyncImage(url: imageURL) { phase in
                    switch phase {
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                    default:
                        placeholder
                    }
                }
            } else {
                placeholder
            }
        }
        .clipped()
    }

    private var placeholder: some View {
        ZStack {
            Color.gray.opacity(0.2)
            Image(systemName: "photo")
                .font(.largeTitle)
                .foregroundColor(.gray)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
