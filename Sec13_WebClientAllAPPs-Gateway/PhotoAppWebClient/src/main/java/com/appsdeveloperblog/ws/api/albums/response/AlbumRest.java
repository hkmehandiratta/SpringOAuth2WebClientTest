package com.appsdeveloperblog.ws.api.albums.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRest {
	private String userId;

	@Getter
	@Setter
	private String albumId;

	@Getter
	@Setter
	private String albumTitle;

	@Getter
	@Setter
	private String albumDescription;

	@Getter
	@Setter
	private String albumUrl;

}
