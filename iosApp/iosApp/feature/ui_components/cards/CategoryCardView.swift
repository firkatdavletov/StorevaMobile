//
//  CategoryCardView.swift
//  iosApp
//
//  Created by Фиркат Давлетов on 09.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct CategoryCardView: View {
    let title: String
    let imageUrl: String?

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            RemoteImage(urlString: imageUrl)
                .frame(height: 96)
                .clipShape(
                    RoundedRectangle(cornerRadius: 12, style: .continuous)
                )

            Text(title)
                .font(AppTypography.bodyMedium)
                .foregroundStyle(Color.onBackground)
                .lineLimit(2)
                .multilineTextAlignment(.leading)
        }
        .frame(height: 234)
        .padding(8)
        .background(Color.surface)
        .clipShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        .overlay {
            RoundedRectangle(cornerRadius: 16, style: .continuous)
                .stroke(Color.primaryContainer, lineWidth: 1)
        }
        .contentShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
    }
}
