package pl.coderslab.dto;

import lombok.Builder;

import pl.coderslab.model.User;

@Builder
public record SendMessageDTO(User userReceiver,String content) {
}
