//
//  RSCarPort.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RSCarPort.h"

@interface RSCarPort ()
    
@property (nonatomic, strong) NSMutableArray<RSCar*> * trackPull;
@property (nonatomic, strong) NSMutableArray<id<RSEnterable>>* enterPull;

@end

@implementation RSCarPort

- (instancetype)init
{
    self = [super init];
    if (self) {
        _trackPull = [NSMutableArray new];
    }
    return self;
}

- (void)launchCar:(RSCar *)car {
    [self.trackPull addObject:car];
    [car toggleEngine:TRUE];
    [car move];
    
    for (RSCar* currentCar in self.trackPull) {
        NSLog(@"%p - %@", currentCar, currentCar.captain.name);
    }
}

- (void)enter:(id<RSEnterable>)entry {
    [self.enterPull addObject:entry];
    NSLog(@"%@ is added!", entry.identifier);
}

@end
