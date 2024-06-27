package pl.coderslab.dto;

import lombok.Builder;

import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;

@Builder
public record SendMessageDTO(UserDetails userReceiver, String content) {
}
