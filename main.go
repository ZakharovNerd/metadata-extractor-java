package main

import (
	"context"
	"fmt"
	"google.golang.org/grpc"
	"mafia/mafia"
	"log"
	"math/rand"
	"net"
)

type server struct {
	mafia.UnimplementedReverseServer
}

var clients = make(map[string]string)
var status_of_clients = make(map[string]string)
var clients_name []string

var clientCard = []string{"mafia", "mafia", "civilian", "doctor"}

func EndOfGame() (bool, string) {
	countMafia := 0
	countCivilian := 0
	for i, _ := range clients_name {
		log.Printf("client name %s", clients_name[i])
		log.Printf("status client %s", status_of_clients[clients_name[i]])
		log.Printf("card client  %s", clients[clients_name[i]])
		//if clients_name[i] == "" {
		//	continue
		//}
		if clients[clients_name[i]] == "mafia" && status_of_clients[clients_name[i]] != "died" {
			countMafia += 1
		} else {
			if status_of_clients[clients_name[i]] != "died" {
				countCivilian += 1
			}
		}
	}
	log.Printf("%v %v", countMafia, countCivilian)
	if countMafia == 1 && countCivilian == 1 {
		return true, "civilian"
	}
	if countMafia == 0 && countCivilian == 2 {
		return true, "civilian"
	}
	if countMafia == 2 && countCivilian == 0 {
		return true, "mafia"
	}
	return false, ""
}

func (s *server) PingMembers(ctx context.Context, request *mafia.Request) (*mafia.PingResponse, error) {
	flagEndGame, winner := EndOfGame()
	if flagEndGame {
		return &mafia.PingResponse{Message: "END", Name1: winner,
			Name2: clients_name[1], Name3: clients_name[2], Name4: clients_name[3]}, nil
	}
	for i := 0; i < len(clients); i++ {
		if status_of_clients[clients_name[i]] == "died" {
			log.Printf("UUUUU %s", clients_name[i])
			clients_name[i] = ""
		}
	}
	return &mafia.PingResponse{Message: "OK", Name1: clients_name[0],
		Name2: clients_name[1], Name3: clients_name[2], Name4: clients_name[3]}, nil
}

func (s *server) Ping(ctx context.Context, request *mafia.Request) (*mafia.PingResponse, error) {
	if len(clientCard) == 0 {
		for i := 1; i < len(clients); i++ {
			if status_of_clients[clients_name[i]] == "died" {
				log.Printf("UUUUU %s", clients_name[i])
				clients_name[i] = ""
			}
		}
		return &mafia.PingResponse{Message: "OK", Name1: clients_name[0],
			Name2: clients_name[1], Name3: clients_name[2], Name4: clients_name[3]}, nil
	}
	return &mafia.PingResponse{Message: "NO", Name1: "", Name2: "", Name3: "", Name4: ""}, nil
}

func (s *server) Exit(ctx context.Context, request *mafia.Request) (*mafia.Response, error) {
	status_of_clients[request.GetMessage()] = "died"
	log.Printf("AAAAA %s", request.GetMessage())
	return &mafia.Response{Message: "OK"}, nil
}

func (s *server) ChangeTime(ctx context.Context, request *mafia.Request) (*mafia.Response, error) {
	if status_of_clients[request.GetMessage()] == "died" {
		return &mafia.Response{Message: "YES"}, nil
	}
	return &mafia.Response{Message: "NO"}, nil
}

var vote = make(map[string]int)
var flag = 0

var nightFlag = 0

func (s *server) NightVote(ctx context.Context, request *mafia.Request) (*mafia.Response, error) {
	flagEndGame, winner := EndOfGame()
	if flagEndGame {
		return &mafia.Response{Message: winner}, nil
	}
	status_of_clients[request.GetMessage()] = "died"
	return &mafia.Response{Message: "OK"}, nil
}

func (s *server) Vote(ctx context.Context, request *mafia.Request) (*mafia.Response, error) {
	flagEndGame, winner := EndOfGame()
	if flagEndGame {
		return &mafia.Response{Message: winner}, nil
	}
	if flag == 0 {
		flag = 1
		totalNumVote = 0
		for key, _ := range vote {
			vote[key] = 0
		}
	}
	client := request.Message
	vote[client] += 1
	totalNumVote += 1
	return &mafia.Response{Message: "OK"}, nil
}

var totalNumVote = 0

func (s *server) PingResultDay(ctx context.Context, request *mafia.Request) (*mafia.ResultVoteDay, error) {
	flagEndGame, winner := EndOfGame()
	if flagEndGame {
		return &mafia.ResultVoteDay{Status: "END", Spirit: winner}, nil
	}
	maxim := 0
	challenger := ""
	log.Printf("%v", vote)
	for key, value := range vote {
		if value >= maxim {
			maxim = value
			challenger = key
		}
	}
	if totalNumVote%4 == 0 {
		log.Printf("%s", challenger)
		flag = 1
		status_of_clients[challenger] = "died"
		return &mafia.ResultVoteDay{Status: "OK", Spirit: challenger}, nil
	}
	return &mafia.ResultVoteDay{Status: "NO", Spirit: ""}, nil
}

func (s *server) Name(ctx context.Context, request *mafia.Request) (*mafia.Response, error) {
	if len(clientCard) != 0 {
		log.Println(fmt.Sprintf("New client: %s", request.GetMessage()))
		var randInd = rand.Intn(len(clientCard))
		var randCard = clientCard[randInd]
		clients[request.GetMessage()] = randCard
		status_of_clients[request.GetMessage()] = "alive"
		vote[request.GetMessage()] = 0
		clientCard[randInd] = clientCard[len(clientCard)-1]
		clientCard[len(clientCard)-1] = ""
		clientCard = clientCard[:len(clientCard)-1]
		clients_name = append(clients_name, request.GetMessage())
		return &mafia.Response{Message: fmt.Sprintf("%s", clients[request.GetMessage()])}, nil
	} else {
		return &mafia.Response{Message: fmt.Sprintf("There are no places")}, nil
	}

}

func main() {
	lis, err := net.Listen("tcp", ":9000")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	srv := grpc.NewServer()
	mafia.RegisterReverseServer(srv, &server{})


	log.Fatalln(srv.Serve(lis))

}
