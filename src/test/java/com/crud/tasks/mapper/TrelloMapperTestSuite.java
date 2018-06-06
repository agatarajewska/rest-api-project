package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardDtoTest() {

        //Given
        List<TrelloList> trelloLists = trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("trelloListId", "listName", true);
        TrelloList trelloList2 = new TrelloList("trelloListId2", "listName2", false);
        trelloLists.add(trelloList);
        trelloLists.add(trelloList2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("testId", "testName", trelloLists));
        trelloBoards.add(new TrelloBoard("testId2", "testName2", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        Assert.assertEquals(trelloBoardDtoList.get(0).getId(), "testId");
        Assert.assertEquals(trelloBoardDtoList.get(0).getId(), "testId");
        Assert.assertEquals(trelloBoardDtoList.get(0).getLists().size(), 2);
    }

    @Test
    public void mapToBoardTest() {

        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("trelloListId", "listName", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("trelloListId2", "listName2", false);
        trelloListDtos.add(trelloListDto);
        trelloListDtos.add(trelloListDto2);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("testId", "testName", trelloListDtos));
        trelloBoardsDto.add(new TrelloBoardDto("testId2", "testName2", trelloListDtos));

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoard(trelloBoardsDto);

        //Then
        Assert.assertEquals(trelloBoardList.get(0).getId(), "testId");
        Assert.assertEquals(trelloBoardList.get(0).getId(), "testId");
        Assert.assertEquals(trelloBoardList.get(0).getLists().size(), 2);
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "testDesc", "testPoz", "testListId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCard.getName(), "testName");
        Assert.assertEquals(trelloCard.getPos(), "testPoz");

    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testName", "testDesc", "testPoz", "testListId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCardDto.getName(), "testName");
        Assert.assertEquals(trelloCardDto.getPos(), "testPoz");
    }

}
