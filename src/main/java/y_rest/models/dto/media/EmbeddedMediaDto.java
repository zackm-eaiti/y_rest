package y_rest.models.dto.media;

import y_rest.models.entity.Media;

/*
this is used when looking at media from within a tweet.
we don't need anything except the url and the type, as this is stored within a tweet
we will only ever deal with media at the same time as tweets.
even when going to the media tab, we will still be dealing with tweets,
just with a spin on how we render them
 */
public record EmbeddedMediaDto(
        MediaType mediaType,
        String mediaUrl
) {
    public static EmbeddedMediaDto fromMedia(Media media) {
        return new EmbeddedMediaDto(
                MediaType.valueOf(media.getMediaType()),
                media.getUrl()
        );
    }
}