import SwiftUI

struct RemoteImageCircle: View {
    let urlString: String

    private var imageURL: URL? {
        URL(string: urlString)
    }

    var body: some View {
        if let imageURL = imageURL {
            AsyncImage(url: imageURL) { phase in
                switch phase {
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 44, height: 44)
                        .clipShape(Circle())
                default:
                    placeholder
                }
            }
        } else {
            placeholder
        }
    }

    private var placeholder: some View {
        Circle()
            .fill(Color.surface)
            .frame(width: 44, height: 44)
    }
}