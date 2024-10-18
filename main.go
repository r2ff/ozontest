package main

import (
	"fmt"
	"math/rand"
	"sync"
)

// 1. Что делает программа и в чем ее можно использовать?
// 2. Поправьте программу так, чтобы она предсказуемо отдавала то, что от нее нужно.
// 3. Напишите тесты на методы генерации

type testData struct {
	phones []int
	mu     sync.Mutex
}

func (td *testData) add() {
	td.mu.Lock()
	defer td.mu.Unlock()
	td.phones = append(td.phones, randPhone())
}

func generate(n int, td *testData) *testData {
	var wg sync.WaitGroup

	for i := 0; i < n; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			td.add()
		}()
	}
	wg.Wait()
	return td
}

func main() {
	td := testData{}
	generate(100, &td)
	fmt.Println(len(td.phones))
	fmt.Println(td.phones)

}

func randPhone() int {
	return 89000000000 + rand.Intn(999999999-100000000) + 100000000
}
